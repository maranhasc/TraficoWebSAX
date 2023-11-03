package model;

public class Item {
	private String title;
	private String link;
	private String pubDate;
	private String description;
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String title, String link, String pubDate, String description) {
		super();
		this.title = title;
		this.link = link;
		this.pubDate = pubDate;
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Item [title=" + title + ", link=" + link + ", pubDate=" + pubDate + ", description=" + description
				+ "]";
	}
	
}
