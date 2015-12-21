package model;

import java.util.UUID;

public class Artist {
	private String _id;
	private String name;
	private String image;
	private int level;
	private String stage;
	private String date;
	private String start;
	private String end;
	public Artist() {}
	public Artist(String name, String image, int level, String stage, String date, String start, String end) {
		this._id=UUID.randomUUID().toString();
		this.name = name;
		this.image = image;
		this.level = level;
		this.stage = stage;
		this.date = date;
		this.start = start;
		this.end = end;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Artist [_id=" + _id + ", name=" + name + ", image=" + image + ", level=" + level + ", stage=" + stage
				+ ", date=" + date + ", start=" + start + ", end=" + end + "]";
	}
}
