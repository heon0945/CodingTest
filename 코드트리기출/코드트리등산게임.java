import java.io.*;

public class 코드트리등산게임 {

    static final int MAX_Q = 500000 + 5000;
    static final int MAX_H = 1000000;

    static final int BIGBANG = 100;
    static final int MOVE_MOUNTAIN = 200;
    static final int EARTHQUAKE = 300;
    static final int SIMULATION = 400;

    static int Q;
    static int[] mountain = new int[MAX_Q];
    static int mcnt;

    static class Node {
        int value;
        int height;

        Node() {
            this(0, 0);
        }

        Node(int value, int height) {
            this.value = value;
            this.height = height;
        }
    }

    static int N;
    static Node[] tree = new Node[4_004_000];
    static Node[] stack = new Node[MAX_Q];
    static int sp;

    static long[] answer = new long[MAX_Q];

    static {
        for (int i = 0; i < tree.length; i++) tree[i] = new Node();
        for (int i = 0; i < stack.length; i++) stack[i] = new Node();
    }

    static void update(int left, int right, int node, int index, Node newNode) {
        if (index < left || right < index) return;

        if (left == right) {
            tree[node].value = newNode.value;
            tree[node].height = newNode.height;
            return;
        }

        int mid = (left + right) >>> 1;
        int l = node << 1;
        int r = l | 1;

        update(left, mid, l, index, newNode);
        update(mid + 1, right, r, index, newNode);

        Node ln = tree[l];
        Node rn = tree[r];

        if (ln.value > rn.value ||
                (ln.value == rn.value && ln.height > rn.height)) {
            tree[node].value = ln.value;
            tree[node].height = ln.height;
        } else {
            tree[node].value = rn.value;
            tree[node].height = rn.height;
        }
    }

    static Node getMax(int left, int right, int a, int b, int node) {
        if (b < left || right < a) return new Node(0, 0);
        if (a <= left && right <= b) return tree[node];

        int mid = (left + right) >>> 1;

        Node ln = getMax(left, mid, a, b, node << 1);
        Node rn = getMax(mid + 1, right, a, b, (node << 1) | 1);

        if (ln.value > rn.value ||
                (ln.value == rn.value && ln.height > rn.height))
            return ln;
        return rn;
    }

    static void input(FastScanner fs) throws Exception {
        N = fs.nextInt();

        for (int i = 1; i <= N; i++) mountain[i] = fs.nextInt();
        mcnt = N + 1;

        for (int i = 1; i <= N; i++) {
            int h = mountain[i];

            Node cur = getMax(1, MAX_H, h, h, 1);
            stack[sp].height = h;
            stack[sp++].value = cur.value;

            Node max = getMax(1, MAX_H, 1, h - 1, 1);
            update(1, MAX_H, 1, h, new Node(max.value + 1, h));
            answer[i] = max.value + 1;
        }
    }

    static void moveMountain(int height) {
        mountain[mcnt++] = height;

        Node cur = getMax(1, MAX_H, height, height, 1);
        stack[sp].height = height;
        stack[sp++].value = cur.value;

        Node max = getMax(1, MAX_H, 1, height - 1, 1);
        update(1, MAX_H, 1, height, new Node(max.value + 1, height));
        answer[mcnt - 1] = max.value + 1;
    }

    static void earthquake() {
        int eqHeight = mountain[--mcnt];
        Node before = stack[--sp];
        update(1, MAX_H, 1, eqHeight, new Node(before.value, before.height));
    }

    static long simulate(int idx) {
        Node total = getMax(1, MAX_H, 1, MAX_H, 1);
        return ((long)(total.value - 1) + answer[idx]) * MAX_H + total.height;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        Q = fs.nextInt();

        while (Q-- > 0) {
            int cmd = fs.nextInt();

            if (cmd == BIGBANG) input(fs);
            else if (cmd == MOVE_MOUNTAIN) moveMountain(fs.nextInt());
            else if (cmd == EARTHQUAKE) earthquake();
            else if (cmd == SIMULATION) sb.append(simulate(fs.nextInt())).append('\n');
        }

        System.out.print(sb);
    }

    // 🔥 Fast Input
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do c = read(); while (c <= ' ');
            if (c == '-') {
                sign = -1;
                c = read();
            }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}
