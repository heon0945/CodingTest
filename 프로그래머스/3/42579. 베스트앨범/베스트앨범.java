import java.util.*;

class Solution {
    
    class Song implements Comparable<Song>{
        int idx;
        int plays;
        
        public Song(int idx, int plays){
            this.idx = idx;
            this.plays = plays;
        }
        
        public int compareTo(Song o){
            return o.plays - plays;
        }
    }
    
    class Genre implements Comparable<Genre>{
        String name;
        long plays;
        
        public Genre(String name, long plays){
            this.name = name;
            this.plays = plays;
        }
        
        public int compareTo(Genre o){
            return Long.compare(o.plays, this.plays);
        }
    }
    
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Song>> songMap = new HashMap<>();
        Map<String, Long> genreMap = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            String genreName = genres[i];
            int playTime = plays[i];
            
            if(!genreMap.containsKey(genreName))
                genreMap.put(genreName, (long)playTime);
            else
                genreMap.put(genreName, genreMap.get(genreName) + playTime);
            
            
            if(!songMap.containsKey(genreName))
                songMap.put(genreName, new ArrayList<>());
            
            songMap.get(genreName).add(new Song(i, playTime));
        }
        
        List<Genre> genreList = new ArrayList<>();
        
        for(String key : genreMap.keySet()){
            genreList.add(new Genre(key, genreMap.get(key)));
        }
        
        Collections.sort(genreList);
        
        List<Integer> album = new ArrayList<>();
        for(Genre popularGenre : genreList){
            
            List<Song> songList = songMap.get(popularGenre.name);
            
            Collections.sort(songList);
            
            album.add(songList.get(0).idx);
            
            if(songList.size() > 1) album.add(songList.get(1).idx);
        }
        
        int[] albumArray = new int[album.size()];
        for(int i = 0; i < album.size(); i++){
            albumArray[i] = album.get(i);
        }
        
        return albumArray;
        
    }
}