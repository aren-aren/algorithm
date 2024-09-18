class Solution {
    fun solution(gems: Array<String>): IntArray {
        val gemsCount = gems.toSet().size;
        val map = hashMapOf<String, Int>();

        var start = 0;

        val answer = intArrayOf(start, gems.size);

        for((index, gem) in gems.withIndex()){
            map[gem] = map.getOrDefault(gem, 0) + 1;

            while(map.size == gemsCount){
                if(index - start < answer[1] - answer[0]){
                    answer[0] = start + 1;
                    answer[1] = index + 1;
                }
                map[gems[start]] = map[gems[start]]!! - 1
                if (map[gems[start]]!! == 0) map.remove(gems[start])
                start++
            }
        }

        return answer;
    }
}
