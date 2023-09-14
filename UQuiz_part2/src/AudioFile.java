//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class models the AudioFile data type. It contains information about the title and artist of
 * an audio file, as well as the duration of the audio in number of seconds.
 * 
 * NOTES: You may NOT add any additional data fields to this class unless specified in the TODO
 * tags. You may NOT add ANY additional methods to this class, regardless of access modifier. There
 * is no tester or main method for this class.
 */
public class AudioFile {
  // TODO
  // 1. Declare a private instance field of type int named duration
  private int duration;

  // 2. Declare a private instance field of type String named title
  private String title;

  // 3. Declare a private instance field of type String named artist
  private String artist;

  // PROVIDED: a class field to store shallow copies of all played AudioFiles
  protected static ArrayList<AudioFile> playedAudioFiles = new ArrayList<AudioFile>();

  // PROVIDED: a class field to store all names of registered artists of AudioFiles
  private static ArrayList<String> registeredArtists =
      new ArrayList<String>(Arrays.asList("Beyonce", "Hozier", "Parliament-Funkadelic",
          "Cecil Baldwin", "Jonathan Sims", "Steve Shell", "Lil Nas X", "Nicki Minaj",
          "Megan Thee Stallion", "SZA", "The HU", "Drake", "Bastille", "DISPATCH"));

  /**
   * Creates a new AudioFile with the given title/artist and duration.
   * 
   * @param duration the duration of the audio file in seconds, must be >=0
   * @param title    the title of the audio file
   * @param artist   the artist of the audio file
   * @throws IllegalArgumentException if duration is negative, or if the artist is NOT in the
   *                                  registeredArtists list
   */
  public AudioFile(int duration, String title, String artist) throws IllegalArgumentException {
    // TODO
    // 5. Check the validity of the input parameters and handle appropriately
    // Hint: use ArrayList's .contains() method
    if (duration < 0) {
      throw new IllegalArgumentException("Error, duration is negative");
    }
    if(!registeredArtists.contains(artist)) {
      throw new IllegalArgumentException("Error, artist is NOT in the registeredArtists list");
    }

    // 6. Set the instance data fields to the provided input parameters
    this.duration = duration;
    this.title = title;
    this.artist = artist;
  }

  /////////////// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) ///////////////

  /**
   * Adds a reference to this AudioFile to the playedAudioFiles ArrayList
   */
  public void listen() {
    // TODO
    // 7. Complete the implementation of this mutator method
    this.playedAudioFiles.add(this);
  }

  /**
   * Adds the specified number of seconds to the duration of this AudioFile. This method does NOT
   * perform any validation on the duration or throw any exceptions.
   * 
   * @param duration the number of seconds to add
   */
  public void addTime(int duration) {
    // TODO
    // 8. Complete the implementation of this mutator method
    this.duration += duration;
  }

  /**
   * Returns a formatted String of the duration as number of minutes + ":" + number of seconds.
   * Number of minutes may have any number of digits, but number of seconds must always have two, so
   * a 62 second duration should produce the result "1:02", NOT "1:2"
   * 
   * @return a formatted String version of the duration split into minutes and seconds
   */
  public String getDuration() {
    // TODO
    // 9. Complete the implementation of this accessor method

    // Hint: minutes can be calculated from seconds using integer division by 60
    int minutes = duration / 60;

    // Hint: remaining seconds can be calculated using modulo 60
    int seconds = duration % 60;

    // Hint: Be sure to check whether the remaining seconds are < 10 and add a leading 0 if so
    String formattedDuration = String.format("%d:%02d", minutes, seconds);

    return formattedDuration;

  }

  /**
   * Returns a string representation of this AudioFile. The returned string has the following
   * format: "<[title]> by [artist] ([duration])" For example: <ALIEN SUPERSTAR> by Beyonce (3:40)
   * <Nina Cried Power> by Hozier (3:45) <One Nation Under A Groove> by Parliament-Funkadelic
   * (11:26)
   * 
   * @return a string formatted as "<title> by artist (duration)".
   */
  @Override
  public String toString() {
    // TODO
    // 10. Complete the implementation of this method
    String formattedDuration = getDuration();
    String result =
        String.format("<%s> by %s (%s)", title, artist, formattedDuration);
    return result;
  }
}

/////////////// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) ///////////////


/**
 * This class models the PodcastEpisode data type, representing an audio file which is part of a
 * podcast series.
 * 
 * NOTES: You may NOT add any additional data fields to this class. You may NOT add ANY additional
 * methods to this class, regardless of access modifier. There is no tester or main method for this
 * class.
 */
class PodcastEpisode extends AudioFile { // TODO: 11. Set this class to be a subclass of the
                                         // AudioFile class

  private String seriesName; // the name of this podcast series
  private int episodeNumber; // the episode number of this particular podcast audio file

  /**
   * Creates a new PodcastEpisode with the provided values
   * 
   * @param seriesName    the name of this podcast series
   * @param episodeNumber the episode number of this episode
   * @param title         the title of this episode
   * @param artist        the artist for this episode
   * @param duration      the duration of this episode
   * 
   * @throws IllegalStateException    if the episode number is <= 0
   * @throws IllegalArgumentException if the super constructor detects bad arguments
   */
  public PodcastEpisode(String seriesName, int episodeNumber, String title, String artist,
      int duration) throws IllegalStateException, IllegalArgumentException {
    // TODO
    // 12. Call the constructor of the super class with required arguments. Do not catch any
    // exceptions it may throw.
    super(duration, title, artist);

    // 13. Check the validity of the episode number parameter
    if (episodeNumber <= 0) {
      throw new IllegalStateException("Error, he episode number is <= 0");
    }

    // 14. Set the instance fields to the values passed as input
    this.seriesName = seriesName;
    this.episodeNumber = episodeNumber;
  }

  /**
   * Adds a sponsored audio advertisement clip to the end of the audio, lengthening the duration of
   * the audio file by the given amount
   * 
   * @param duration the duration of the advertisement in seconds
   * @throws IllegalArgumentException if the duration is <0
   */
  public void appendAdvertisement(int duration) throws IllegalArgumentException {
    // TODO
    // 15. Complete the implementation of this mutator method, modifying the private superclass
    // data field accordingly
    if (duration < 0) {
      throw new IllegalArgumentException("Error, duration is less than zero");
    }
    super.addTime(duration);
  }

  /**
   * Returns a string representation of this PodcastEpisode. The returned string has the following
   * format, including the "- listened" string if the audio file appears in the playedAudioFiles
   * ArrayList: [seriesName] episode [episodeNumber]: <[title]> by [artist] ([duration]) - listened
   * 
   * Note that the <[title]> by [artist] ([duration]) is the result of calling the super class'
   * toString() method.
   * 
   * Examples: WTNV episode 19: <Sandstorm> by Cecil Baldwin (26:39) - listened TMA episode 160:
   * <The Eye Opens> by Jonathan Sims (32:33) Old Gods episode 12: <The Other Queen> by Steve Shell
   * (27:29) - listened
   * 
   * @return a string formatted as described above
   */

  @Override
  public String toString() {
    // TODO
    // 16. Complete the implementation of this method
    // Note: call the super class implementation of toString() to get the title, artist, and
    // duration
    // of this podcast episode
    // Hint: use ArrayList's .contains() method to see if this object is in playedAudioFiles
    String result = super.toString();

    if (playedAudioFiles.contains(this)) {
      return this.seriesName+" episode "+this.episodeNumber+": "+result + " - listened";
    }
    return this.seriesName+" episode "+this.episodeNumber+": "+result;
  }

  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope

}
