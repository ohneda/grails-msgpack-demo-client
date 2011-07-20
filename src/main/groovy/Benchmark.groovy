import org.apache.commons.lang.time.StopWatch
class Benchmark {
    def result = []
    StopWatch sw = new StopWatch()
    public void start( Closure cls){

        sw.start()

        cls.call()

        sw.stop()
        result << sw.time
        sw.reset()

    }
}
