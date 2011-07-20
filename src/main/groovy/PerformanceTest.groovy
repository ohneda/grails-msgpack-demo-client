JsonClient client = new JsonClient('http://localhost:8080/grails-msgpack-demo/')
MessagePackRpcClient rclient = new MessagePackRpcClient("localhost", 1985)

def benchmark

// get
benchmark = rclient.get(0..50 )
benchmark.remove(0) // initial access is too slow
println( "MessagePack get 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

benchmark = client.get(0..50)
benchmark.remove(0) // initial access is too slow
println( "Json get 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

// save
benchmark = rclient.save(0..50)
println( "MessagePack post 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

benchmark = client.save(0..50)
println( "Json post 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

//update
benchmark = rclient.update(0..50)
println( "MessagePack update 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

benchmark = client.update(0..50)
println( "Json update 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

//delete
benchmark = rclient.delete(100..150)
println( "MessagePack delete 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

benchmark = client.delete(151..161)
println( "Json de,ete 50 times")
use(StandardDeviationCategory){ println benchmark.stat() }

rclient.shutdown()