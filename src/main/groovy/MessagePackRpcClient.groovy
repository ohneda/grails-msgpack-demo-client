import java.util.Map;

import org.grails.msgpack.*
import org.grails.msgpack.demo.Message
import org.grails.msgpack.demo.Comment
import org.grails.msgpack.demo.MessageService
import org.msgpack.rpc.Client
import org.msgpack.rpc.loop.EventLoop
import org.msgpack.template.*
import org.msgpack.template.builder.*
import org.msgpack.*
import org.apache.commons.lang.time.StopWatch

/**
 * This service class is not for production use, and doesn't be included in plugin package.
 *
 * @author ohneda
 *
 */
public class MessagePackRpcClient {

    Client cli
    MessageService iface

    public MessagePackRpcClient(String host, Integer port){
        MessagePack.register(Message)
        MessagePack.register(Comment)
        EventLoop loop = EventLoop.defaultEventLoop()
        cli = new Client(host, port, loop)
        iface = cli.proxy(MessageService.class)
    }

    List get(range){
        def benchmark = new Benchmark()
        range.each{ index ->
            benchmark.start {
                Message result = iface.get(index+1)
            }
        }
        benchmark.result
    }

    List save(range){
        def benchmark = new Benchmark()
        range.each{ index ->

            Message message = new Message(
                    doubleval: 3.4028234663852886E38,
                    floatval: 0.1,
                    intval: 0,
                    isActive: true,
                    longval: 2147483647,
                    optional: "Optional Value-${index}",
                    props: ['key-1':'value-1', 'key-2':'value-2'],
                    string: "Msgpack RPC Save ${index}",
                    dateCreated: new Date(),
                    dateUpdated: new Date()
                    )
            message.comments = new ArrayList<Comment>()
            message.props = new HashMap<String, String>()
            message.props['key-1'] = 'value-1'
            message.props['key-2'] = 'value-2'
            message.comments << new Comment(title: 'title', name: 'name')

            benchmark.start {
                Message result = iface.save(message)
                assert result.id
            }
        }
        benchmark.result
    }

    List update(range){
        def benchmark = new Benchmark()
        range.each{ index ->

            Message message = iface.get(index+1)
            def updateString = "${message.string} Mesgpack RPC Update ${new Date()}"
            message.string = updateString

            benchmark.start {
                Message result = iface.update(message)
                assert result.id
            }

            assert iface.get(message.id ).string == updateString

        }
        benchmark.result
    }

    List delete(range){
        def benchmark = new Benchmark()
        range.each{ index ->
            benchmark.start {
                Long result = iface.delete(index+1)
                assert result == index + 1
            }
        }
        benchmark.result
    }

    def shutdown(){
        cli.close()
        cli.eventLoop.shutdown()
        //loop.shutdown()
    }
}

