import groovyx.net.http.*
import groovy.json.*
import static groovyx.net.http.ContentType.*
import org.apache.commons.lang.time.StopWatch
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation


public class JsonClient {

    def url = 'http://localhost:8080/grails-msgpack-demo/'
    def http

    public JsonClient(String url){
        this.url = url
        http = new RESTClient( url )
    }

    List get(range){
        def benchmark = new Benchmark()
        range.each { index ->
            benchmark.start{
                def resp = http.get( path: "message/${index+1}")
                assert resp.data.id == (index + 1)
            }
        }

        benchmark.result
    }

    List save(range){
        def benchmark = new Benchmark()
        range.each { index -> 

            benchmark.start{

                def json = new JsonBuilder()
                json {
                    doubleval 3.40282346
                    floatval 0.1
                    intval 0
                    isActive true
                    longval 2147483647
                    optional 'Json Save Value-${index}'
                    props 'key-1':'value-1', 'key-2':'value-2'
                    string "Json Save String ${index}"
                    comments ([title: 'title1', name: 'name1'],[title: 'title1', name: 'name1'])
                }

                def resp = http.post( path: 'message', body: json.toString(),
                        requestContentType: JSON )
                assert resp.data.id
            }
        }
        benchmark.result
    }

    List update(range){
        def benchmark = new Benchmark()
        range.each { index -> 
            def resp = http.get( path: "message/${index+1}")
            def targetId = resp.data.id

            benchmark.start{

                def json = new JsonBuilder()
                def updateString = "${resp.data.string} : Json Update String ${new Date()}"
                json {
                    id targetId
                    doubleval 3.40282346
                    floatval 0.1
                    intval 0
                    isActive true
                    longval 2147483647
                    optional "Json Save Value-${index}"
                    props 'key-1':'value-1', 'key-2':'value-2'
                    string updateString
                }

               resp = http.put( path: "message/${targetId}", body: json.toString(),
                        requestContentType: JSON )
                assert resp.data.string == updateString
            }
        }
        benchmark.result
    }
    
    List delete(range){
        def benchmark = new Benchmark()
        range.each { index ->
            benchmark.start {
                def resp = http.delete( path: "message/${index+1}")
                assert resp.data.id == "${(index + 1)}"
            }
        }

        benchmark.result
    }
}


