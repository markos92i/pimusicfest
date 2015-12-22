package model;

import java.util.ArrayList;
import java.util.UUID;

public class LineUp {
	private String _id;
	private String name;
	private ArrayList<String> artists;
	private ArrayList<String> sponsors;

    public LineUp() {} //Allows Serialization
	public LineUp(String name, ArrayList<String> artists, ArrayList<String> sponsors) {
		this._id=UUID.randomUUID().toString();
		this.name = name;
		this.artists = artists;
		this.sponsors = sponsors;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getArtists() {
		return artists;
	}
	public void setArtists(ArrayList<String> artists) {
		this.artists = artists;
	}
	public ArrayList<String> getSponsors() {
		return sponsors;
	}
	public void setSponsors(ArrayList<String> sponsors) {
		this.sponsors = sponsors;
	}
	@Override
	public String toString() {
		return "LineUp [_id=" + _id + ", name=" + name + ", artists=" + artists + ", sponsors=" + sponsors + "]";
	}
}
