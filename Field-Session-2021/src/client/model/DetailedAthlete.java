/*
 * Strava API v3
 * The [Swagger Playground](https://developers.strava.com/playground) is the easiest way to familiarize yourself with the Strava API by submitting HTTP requests and observing the responses before you write any client code. It will show what a response will look like with different endpoints depending on the authorization scope you receive from your athletes. To use the Playground, go to https://www.strava.com/settings/api and change your “Authorization Callback Domain” to developers.strava.com. Please note, we only support Swagger 2.0. There is a known issue where you can only select one scope at a time. For more information, please check the section “client code” at https://developers.strava.com/docs.
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.SummaryAthlete;
import io.swagger.client.model.SummaryClub;
import io.swagger.client.model.SummaryGear;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * DetailedAthlete
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-05-25T13:19:33.024-06:00")
public class DetailedAthlete extends SummaryAthlete {
  @SerializedName("follower_count")
  private Integer followerCount = null;

  @SerializedName("friend_count")
  private Integer friendCount = null;

  /**
   * The athlete&#39;s preferred unit system.
   */
  @JsonAdapter(MeasurementPreferenceEnum.Adapter.class)
  public enum MeasurementPreferenceEnum {
    FEET("feet"),
    
    METERS("meters");

    private String value;

    MeasurementPreferenceEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static MeasurementPreferenceEnum fromValue(String text) {
      for (MeasurementPreferenceEnum b : MeasurementPreferenceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<MeasurementPreferenceEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final MeasurementPreferenceEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public MeasurementPreferenceEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return MeasurementPreferenceEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("measurement_preference")
  private MeasurementPreferenceEnum measurementPreference = null;

  @SerializedName("ftp")
  private Integer ftp = null;

  @SerializedName("weight")
  private Float weight = null;

  @SerializedName("clubs")
  private List<SummaryClub> clubs = null;

  @SerializedName("bikes")
  private List<SummaryGear> bikes = null;

  @SerializedName("shoes")
  private List<SummaryGear> shoes = null;

  public DetailedAthlete followerCount(Integer followerCount) {
    this.followerCount = followerCount;
    return this;
  }

   /**
   * The athlete&#39;s follower count.
   * @return followerCount
  **/
  @ApiModelProperty(value = "The athlete's follower count.")
  public Integer getFollowerCount() {
    return followerCount;
  }

  public void setFollowerCount(Integer followerCount) {
    this.followerCount = followerCount;
  }

  public DetailedAthlete friendCount(Integer friendCount) {
    this.friendCount = friendCount;
    return this;
  }

   /**
   * The athlete&#39;s friend count.
   * @return friendCount
  **/
  @ApiModelProperty(value = "The athlete's friend count.")
  public Integer getFriendCount() {
    return friendCount;
  }

  public void setFriendCount(Integer friendCount) {
    this.friendCount = friendCount;
  }

  public DetailedAthlete measurementPreference(MeasurementPreferenceEnum measurementPreference) {
    this.measurementPreference = measurementPreference;
    return this;
  }

   /**
   * The athlete&#39;s preferred unit system.
   * @return measurementPreference
  **/
  @ApiModelProperty(value = "The athlete's preferred unit system.")
  public MeasurementPreferenceEnum getMeasurementPreference() {
    return measurementPreference;
  }

  public void setMeasurementPreference(MeasurementPreferenceEnum measurementPreference) {
    this.measurementPreference = measurementPreference;
  }

  public DetailedAthlete ftp(Integer ftp) {
    this.ftp = ftp;
    return this;
  }

   /**
   * The athlete&#39;s FTP (Functional Threshold Power).
   * @return ftp
  **/
  @ApiModelProperty(value = "The athlete's FTP (Functional Threshold Power).")
  public Integer getFtp() {
    return ftp;
  }

  public void setFtp(Integer ftp) {
    this.ftp = ftp;
  }

  public DetailedAthlete weight(Float weight) {
    this.weight = weight;
    return this;
  }

   /**
   * The athlete&#39;s weight.
   * @return weight
  **/
  @ApiModelProperty(value = "The athlete's weight.")
  public Float getWeight() {
    return weight;
  }

  public void setWeight(Float weight) {
    this.weight = weight;
  }

  public DetailedAthlete clubs(List<SummaryClub> clubs) {
    this.clubs = clubs;
    return this;
  }

  public DetailedAthlete addClubsItem(SummaryClub clubsItem) {
    if (this.clubs == null) {
      this.clubs = new ArrayList<SummaryClub>();
    }
    this.clubs.add(clubsItem);
    return this;
  }

   /**
   * The athlete&#39;s clubs.
   * @return clubs
  **/
  @ApiModelProperty(value = "The athlete's clubs.")
  public List<SummaryClub> getClubs() {
    return clubs;
  }

  public void setClubs(List<SummaryClub> clubs) {
    this.clubs = clubs;
  }

  public DetailedAthlete bikes(List<SummaryGear> bikes) {
    this.bikes = bikes;
    return this;
  }

  public DetailedAthlete addBikesItem(SummaryGear bikesItem) {
    if (this.bikes == null) {
      this.bikes = new ArrayList<SummaryGear>();
    }
    this.bikes.add(bikesItem);
    return this;
  }

   /**
   * The athlete&#39;s bikes.
   * @return bikes
  **/
  @ApiModelProperty(value = "The athlete's bikes.")
  public List<SummaryGear> getBikes() {
    return bikes;
  }

  public void setBikes(List<SummaryGear> bikes) {
    this.bikes = bikes;
  }

  public DetailedAthlete shoes(List<SummaryGear> shoes) {
    this.shoes = shoes;
    return this;
  }

  public DetailedAthlete addShoesItem(SummaryGear shoesItem) {
    if (this.shoes == null) {
      this.shoes = new ArrayList<SummaryGear>();
    }
    this.shoes.add(shoesItem);
    return this;
  }

   /**
   * The athlete&#39;s shoes.
   * @return shoes
  **/
  @ApiModelProperty(value = "The athlete's shoes.")
  public List<SummaryGear> getShoes() {
    return shoes;
  }

  public void setShoes(List<SummaryGear> shoes) {
    this.shoes = shoes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailedAthlete detailedAthlete = (DetailedAthlete) o;
    return Objects.equals(this.followerCount, detailedAthlete.followerCount) &&
        Objects.equals(this.friendCount, detailedAthlete.friendCount) &&
        Objects.equals(this.measurementPreference, detailedAthlete.measurementPreference) &&
        Objects.equals(this.ftp, detailedAthlete.ftp) &&
        Objects.equals(this.weight, detailedAthlete.weight) &&
        Objects.equals(this.clubs, detailedAthlete.clubs) &&
        Objects.equals(this.bikes, detailedAthlete.bikes) &&
        Objects.equals(this.shoes, detailedAthlete.shoes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(followerCount, friendCount, measurementPreference, ftp, weight, clubs, bikes, shoes, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailedAthlete {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    followerCount: ").append(toIndentedString(followerCount)).append("\n");
    sb.append("    friendCount: ").append(toIndentedString(friendCount)).append("\n");
    sb.append("    measurementPreference: ").append(toIndentedString(measurementPreference)).append("\n");
    sb.append("    ftp: ").append(toIndentedString(ftp)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    clubs: ").append(toIndentedString(clubs)).append("\n");
    sb.append("    bikes: ").append(toIndentedString(bikes)).append("\n");
    sb.append("    shoes: ").append(toIndentedString(shoes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

