import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    class Id{
        int id;
        String name;

        public Id(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    Set<Integer> set = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        Id[] ids = new Id[user_id.length];
        for (int i = 0; i < user_id.length; i++) {
            ids[i] = new Id(i, user_id[i]);
        }

        List<List<Id>> matchIds = new ArrayList<>();

        for (int i = 0; i < banned_id.length; i++) {
            matchIds.add(new ArrayList<>());

            String regex = banned_id[i].replaceAll("[*]", "\\\\w");
            for (Id id : ids) {
                if(id.name.matches(regex)){
                    matchIds.get(i).add(id);
                }
            }
        }

        countMatch(matchIds, 0, 0);

        return set.size();
    }

    private void countMatch(List<List<Id>> list, int n, int path){
        if(n >= list.size()) {
            set.add(path);
            return;
        }

        List<Id> matchIds = list.get(n);

        int result = 0;

        for (Id matchId : matchIds) {
            if((path&(1<<matchId.id)) > 0) continue;

            countMatch(list, n + 1, path + (1<<matchId.id));
        }
    }
}
