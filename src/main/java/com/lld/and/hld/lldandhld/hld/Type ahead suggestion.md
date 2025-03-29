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

c -> a -> t
c -> a -> p
c -> a -> p -> t -> a -> i -> n
c -> a -> p -> i -> t -> a -> l

Trie {
    // initially empty for each character
    char c;
    int count;
    Map<Integer, Trie> trieMap = new HashMap<>();
}

A-Z = empty Trie

if key contains
    search with the value of the key
else 
    insert value to key and return



Trie root = new Trie();
search(String str) {
    Trie trie = root.search(str)
    trie.getTopList();
}


Functional Requirements:

Non-Functional Requirements:






