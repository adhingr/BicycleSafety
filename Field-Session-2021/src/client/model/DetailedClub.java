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
import io.swagger.client.model.SummaryClub;
import java.io.IOException;

/**
 * DetailedClub
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-05-25T13:19:33.024-06:00")
public class DetailedClub extends SummaryClub {
  /**
   * The membership status of the logged-in athlete.
   */
  @JsonAdapter(MembershipEnum.Adapter.class)
  public enum MembershipEnum {
    MEMBER("member"),
    
    PENDING("pending");

    private String value;

    MembershipEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static MembershipEnum fromValue(String text) {
      for (MembershipEnum b : MembershipEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<MembershipEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final MembershipEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public MembershipEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return MembershipEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("membership")
  private MembershipEnum membership = null;

  @SerializedName("admin")
  private Boolean admin = null;

  @SerializedName("owner")
  private Boolean owner = null;

  @SerializedName("following_count")
  private Integer followingCount = null;

  public DetailedClub membership(MembershipEnum membership) {
    this.membership = membership;
    return this;
  }

   /**
   * The membership status of the logged-in athlete.
   * @return membership
  **/
  @ApiModelProperty(value = "The membership status of the logged-in athlete.")
  public MembershipEnum getMembership() {
    return membership;
  }

  public void setMembership(MembershipEnum membership) {
    this.membership = membership;
  }

  public DetailedClub admin(Boolean admin) {
    this.admin = admin;
    return this;
  }

   /**
   * Whether the currently logged-in athlete is an administrator of this club.
   * @return admin
  **/
  @ApiModelProperty(value = "Whether the currently logged-in athlete is an administrator of this club.")
  public Boolean isAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public DetailedClub owner(Boolean owner) {
    this.owner = owner;
    return this;
  }

   /**
   * Whether the currently logged-in athlete is the owner of this club.
   * @return owner
  **/
  @ApiModelProperty(value = "Whether the currently logged-in athlete is the owner of this club.")
  public Boolean isOwner() {
    return owner;
  }

  public void setOwner(Boolean owner) {
    this.owner = owner;
  }

  public DetailedClub followingCount(Integer followingCount) {
    this.followingCount = followingCount;
    return this;
  }

   /**
   * The number of athletes in the club that the logged-in athlete follows.
   * @return followingCount
  **/
  @ApiModelProperty(value = "The number of athletes in the club that the logged-in athlete follows.")
  public Integer getFollowingCount() {
    return followingCount;
  }

  public void setFollowingCount(Integer followingCount) {
    this.followingCount = followingCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailedClub detailedClub = (DetailedClub) o;
    return Objects.equals(this.membership, detailedClub.membership) &&
        Objects.equals(this.admin, detailedClub.admin) &&
        Objects.equals(this.owner, detailedClub.owner) &&
        Objects.equals(this.followingCount, detailedClub.followingCount) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(membership, admin, owner, followingCount, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailedClub {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    membership: ").append(toIndentedString(membership)).append("\n");
    sb.append("    admin: ").append(toIndentedString(admin)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    followingCount: ").append(toIndentedString(followingCount)).append("\n");
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

