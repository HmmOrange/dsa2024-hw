package hw2;

public class UF1 {
    int[] parent, sz;
    public UF1(int n) {
        parent = new int[n + 1];
        sz = new int[n  + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int u) {
        if (u != parent[u]) return parent[u] = find(parent[u]);
        return u;
    }

    public boolean con(int u, int v) {
        return find(u) == find(v);
    }

    public boolean join(int u, int v) {
        // Return false if already connected, else connect u - v
        u = find(u);
        v = find(v);
        if (u == v) return false;
        if (sz[u] < sz[v]){
            parent[u] = v;
            sz[v] += sz[u];
        }
        else {
            parent[v] = u;
            sz[u] += sz[v];
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
