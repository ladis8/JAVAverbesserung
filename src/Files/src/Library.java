/**
 * Created by ladislav on 12/03/17.
 */
import java.util.List;
import java.util.ArrayList;

class Library<E> {
    private List<E> resources = new ArrayList<E>();
    public void add(E x) {
        resources.add(x);
    }
    public E getLast() {
        int size = resources.size();
        return size > 0 ? resources.get(size-1) : null;
    }
}

