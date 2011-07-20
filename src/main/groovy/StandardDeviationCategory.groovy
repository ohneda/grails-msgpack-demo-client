
class StandardDeviationCategory {
    static Integer mean(List list) {
        list.sum() / list.size()
    }
    
    static Double stdev(List list){
        scale(Math.sqrt(list.collect{ num -> Math.pow((num - list.mean()),2) }.sum()/ (list.size() -1)) as BigDecimal)
    }
    
    static String confidentDeviation(List list) {
        def mean = list.mean()
        def stdev = list.stdev()

        def from = scale((mean - (2*stdev)) as BigDecimal)
        def to = scale((mean + (2*stdev)) as BigDecimal)

        "${from}..${to}"
    }
    
    static String stat(List list){
        "mean: ${list.mean()} standard deviation: ${list.stdev()} confident deviation: ${list.confidentDeviation()}"
    }

    static Double scale(BigDecimal value){
        value.setScale(3, java.math.RoundingMode.CEILING)
    }
}
