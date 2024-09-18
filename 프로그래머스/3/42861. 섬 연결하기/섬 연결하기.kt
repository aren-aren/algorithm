class Solution {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        costs.sortBy { item -> item[2] }
        val parent = IntArray(n) { item -> item }

        fun getParent(target : Int) : Int{
            if(target != parent[target]){
                parent[target] = getParent(parent[target])
            }

            return parent[target]
        }

        var answer : Int = 0

        for(item in costs){
            if(getParent(item[0]) == getParent(item[1])){
                continue
            }
            answer += item[2]

            if(parent[item[0]] < parent[item[1]]){
                parent[getParent(item[1])] = parent[item[0]];
            } else {
                parent[getParent(item[0])] = parent[item[1]];
            }
        }

        return answer;
    }
}