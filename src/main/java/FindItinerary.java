import java.util.*;

class FindItinerary {
    Map<String, List<String>> adj = new HashMap<String, List<String>>();
    int len=0;
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort((a,b)->a.get(1).compareTo(b.get(1)));
        len = tickets.size();
        for(List<String> ticket:tickets)
            adj.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        List<String> res = new ArrayList<>();
        res.add("JFK");
        return dfs("JFK",res) ? res: new ArrayList<>();
    }

    public boolean dfs(String src, List<String> res){

        if(res.size() == len+1)
            return true;

        if(!adj.containsKey(src))
            return false;

        List<String> curr = adj.get(src);
        int s = curr.size();

        for(int i = 0; i < s; i++) {
            String str = curr.get(i);
            adj.get(src).remove(i);
            res.add(str);
            if (dfs(str,res)) return true;
            adj.get(src).add(i,str);
            res.remove(res.size()-1);
        }
        return false;
    }
    public static void main(String arg[]){
        FindItinerary s = new FindItinerary();
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("BUF", "HOU"),
                Arrays.asList("HOU", "SEA"),
                Arrays.asList("JFK", "BUF")
        );
        System.out.println(s.findItinerary(tickets));
    }
}

