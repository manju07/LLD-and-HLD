Type ahead suggestion

- While searching, suggesting recommendations based on recent search and rank.


Trie {
    char c;
    int count;
    Trie[] trie = new Trie[26];
    List<String> topList = new ArrayList<>();
    Trie(char c) {
        this.c = c;
        this.count++;
    }
}

        Root
    /  /  | \ \
    



