package com.pdy.fac.demorestback.hero;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pdy.fac.demorestback.commons.Mappable;

import io.swagger.annotations.ApiModelProperty;

/**
 * Hero
 */

public class Hero implements Mappable {

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	public Hero id(final String id) {
		this.id = id;
		return this;
	}

	public Hero(final String id, final String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Hero() {
		super();
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Hero name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "")

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Hero hero = (Hero) o;
		return Objects.equals(id, hero.id) && Objects.equals(name, hero.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Hero {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
