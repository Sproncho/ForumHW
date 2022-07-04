package telran.forum.model;

import java.time.LocalDate;


public interface Forum {
    public boolean addPost(Post post);
    public boolean removePost(int postId);
    public boolean updatePost(int postId, String newContent);
    public Post getPostById(int postId);
    public Post[] getPostsByAuthor(String author);
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo);
    public int size();
}
