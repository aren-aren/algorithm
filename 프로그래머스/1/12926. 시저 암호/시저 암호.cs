    public class Solution {
        public string solution(string s, int n) {
            string answer = "";

            foreach (var c in s.ToCharArray())
            {
                if (c.Equals(' '))
                {
                    answer += ' ';
                    continue;
                }
                
                int a = c >= 'a' ? 'a' : 'A';
                answer += (char)((c + n - a) % 26 + a);
            }
            
            return answer;
        }
    }