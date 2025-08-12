import java.util.ArrayList;
import java.util.List;

class SnapshotArray {
    private List<int[]> data[];
    private int snapId;

    public SnapshotArray(int length) {
        data = new ArrayList[length];
        for(int i=0;i < length; i++){
            data[i] = new ArrayList<>();
            data[i].add(new int[]{0, 0});
        }
        snapId = 0;
    }

    public void set(int index, int val) {
        List<int[]> curr = data[index];

        if(curr.get(curr.size()-1)[0] == snapId){
            curr.get(curr.size()-1)[1] = val;
        } else {
            curr.add(new int[]{snapId,val});
        }
    }

    public int snap() {
        snapId++;
        return snapId-1;
    }

    public int get(int index, int snap_id) {
        List<int[]> curr = data[index];
        int l = 0;
        int r = curr.size()-1;

        while(l <= r) {
            int mid = (l+r)/2;

            if (curr.get(mid)[0] == snap_id)
                return curr.get(mid)[1];

            if( snap_id > curr.get(mid)[0])
                l = mid + 1;
            else
                r = mid - 1 ;
        }
        return curr.get(r)[1];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */