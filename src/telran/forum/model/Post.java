package telran.forum.model;


import java.time.LocalDateTime;

public class Post implements Comparable<Post>{
    private String author;
    private int postId;
    private String title;
    private String content;
    private LocalDateTime date;
    private int likes;

    public Post(String author, int postId, String title, String content) {
        this.author = author;
        this.postId = postId;
        this.title = title;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        return postId == post.postId;
    }

    @Override
    public int hashCode() {
        return postId;
    }

    @Override
    public int compareTo(Post o) {
        if(date == null && o.date == null)
            return 0;
        else if(date == null)
            return 1;
        else if(o.date == null)
            return -1;

        if(date.isAfter(o.date))
            return 1;
        else if (date.isBefore(o.date))
            return -1;
        else
            return 0;
    }

    int addLike(){
        if(likes < 0)
            return likes;
        else likes+=1;
        return likes;
    }
}
