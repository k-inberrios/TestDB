package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME="music.db";
    public static final String CONNECTION_STRING="jdbc:sqlite:/home/kmaravilla/"+DB_NAME;


    public static final String TABLE_ALBUMS="albums";
    public static final String COLUMN_ALBUM_ID="_id";
    public static final String COLUMN_ALBUM_NAME="name";
    public static final String COLUMN_ALBUM_ARTIST="artist";

    public static final String TABLE_ARTIST="artists";
    public static final String COLUMN_ARTIST_ID="_id";
    public static final String COLUMN_ARTIST_NAME="name";

    public static final String TABLE_SONGS="songs";
    public static final String  COLUMN_SONG_ID="_id";
    public static final String COLUMN_SONG_TRACK="track";
    public static final String COLUMN_SONG_TITLE="title";
    public static final String COLUMN_SONG_ALBUM="album";

    private Connection conn;

    public boolean Open(){

        try
        {
            conn= DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't connect to the database: "+e.getMessage());
            return false;
        }

    }

    public void close(){

        try
        {
            if(conn!=null){
                conn.close();
            }
        }
        catch (SQLException e){
            System.out.println("Couldn't close the connection: "+e.getMessage());

        }

    }

    public List<Song> querySong(){

        Statement st=null;
        ResultSet rs=null;

        try{

            st=conn.createStatement();
            rs=st.executeQuery("SELECT * FROM "+TABLE_SONGS+" LIMIT 100");

            List<Song> songs= new ArrayList<>();

            while(rs.next()){
                Song song=new Song();
                song.setAlbumId(rs.getInt(COLUMN_ALBUM_ID));
                song.setId(rs.getInt(COLUMN_SONG_ID));
                song.setName(rs.getString(COLUMN_SONG_TITLE));
                song.setTrack(rs.getInt(COLUMN_SONG_TRACK));

                songs.add(song);
            }
            return songs;


        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the resultset "+e.getMessage());
            }
            try{
                if(st !=null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the statement "+e.getMessage());
            }
        }

    }

    public List<Artist> queryArtist(){

        Statement statement=null;
        ResultSet results=null;

        try
        {
            statement=conn.createStatement();
            results= statement.executeQuery("SELECT * FROM "+TABLE_ARTIST);

            List<Artist> artists=new ArrayList<>();

            while(results.next()){
                Artist artist=new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        }
        catch (SQLException e){
            System.out.println("Query failed: "+e.getMessage());
            return null;

        }finally{
            try{
                if(results!=null){
                    results.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the resultset "+e.getMessage());
            }
            try{
                if(statement !=null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the statement "+e.getMessage());
            }
        }



    }

    public List<Album> queryAlbum(){

        Statement statement=null;
        ResultSet rs=null;

        try
        {
            statement=conn.createStatement();
            rs=statement.executeQuery("SELECT * FROM "+TABLE_ALBUMS);

            List<Album> albums=new ArrayList<>();
            while(rs.next()){
                Album album=new Album();
                album.setId(rs.getInt(COLUMN_ALBUM_ID));
                album.setName(rs.getString(COLUMN_ALBUM_NAME));
                album.setArtistId(rs.getInt(COLUMN_ALBUM_ARTIST));
                albums.add(album);
            }
            return albums;


        }catch (SQLException e){
            System.out.println("There are no albums: "+e.getMessage());
            return null;

        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the resultset "+e.getMessage());
            }
            try{
                if(statement !=null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the statement "+e.getMessage());
            }

        }



    }

    public List<Artist> queryArtistbyName(String name){

        Statement st=null;
        ResultSet rs=null;

        try
        {
            st=conn.createStatement();
            rs=st.executeQuery("SELECT * FROM "+TABLE_ARTIST+" WHERE "+COLUMN_ARTIST_NAME+" LIKE '%"+name+"%'");

            List<Artist> artists=new ArrayList<>();
            while(rs.next()){
                Artist artist=new Artist();
                artist.setName(rs.getString(COLUMN_ARTIST_NAME));
                artist.setId(rs.getInt(COLUMN_ARTIST_ID));
                artists.add(artist);
            }
            return artists;

        }catch (SQLException e){
            System.out.println("Error getting the data "+e.getMessage());
            return null;
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the resultset "+e.getMessage());
            }
            try{
                if(st !=null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println("Error closing the statement "+e.getMessage());
            }

        }
    }



}
