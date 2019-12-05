package Music;

import Model.Album;
import Model.Artist;
import Model.DataSource;
import Model.Song;

import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        DataSource datasource=new DataSource();


        if(!datasource.Open()){
            System.out.println("Can't  open datasource");
            return;
        }


        /*

        //To query all artists and show them in the console
        List<Artist> artists=datasource.queryArtist();

        if(artists == null){
            System.out.println("no artists");
            return;
        }

        for(Artist artist: artists){
            System.out.println("ID = "+artist.getId()+ " Name = "+ artist.getName());
        }

        */

        /*

        //To query al the albums and show them in the console
        List<Album> albums=datasource.queryAlbum();

        if(albums==null){
            System.out.println("no albums");
            return;
        }

        for(Album album:albums){

            System.out.println(" ID = "+album.getId()+" Name = "+album.getName()+" Artist ID = "+ album.getArtistId() );
        }

         */


        /*
        //To search artist by name


        String artistname= JOptionPane.showInputDialog("Enter the artist to search");

        List<Artist> art=datasource.queryArtistbyName(artistname);

        if(art.isEmpty()){
            System.out.println("Sorry. no artist with such name");
            return;

        }else {
            for(Artist artists:art){
                System.out.println("ID = "+artists.getId()+ " Name = "+ artists.getName());
            }
        }
*/


        //To query all songs
        List<Song> songs=datasource.querySong();

        if(songs==null){
            System.out.println("no albums");
            return;
        }

        for(Song song:songs){

            System.out.println(" ID = "+song.getId()+" Track = "+song.getTrack()+" Title = "+ song.getName()+ " Album = "+song.getAlbumId());
        }

        datasource.close();

    }
}
