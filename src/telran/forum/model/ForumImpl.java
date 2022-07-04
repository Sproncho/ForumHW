package telran.forum.model;

import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;

public class ForumImpl implements Forum{

    private Post[] posts;
    private int size;

    public ForumImpl(int capacity) {
        posts = new Post[capacity];
    }

    @Override
    public boolean addPost(Post post) {
        if (size == posts.length || post == null || findIndUsingPredicate(o -> o.getPostId() == post.getPostId()) != -1) {
            return false;
        }
        int index = Arrays.binarySearch(posts, 0, size, post);
        index = index < 0 ? -index - 1 : index;
        System.arraycopy(posts, index, posts, index + 1, size - index);
        posts[index] = post;
        size++;
        return true;
    }

    @Override
    public boolean removePost(int postId) {
        int ind = findIndUsingPredicate((o) -> o.getPostId() == postId);
        if(ind == -1)
            return false;
        System.arraycopy(posts, ind + 1, posts, ind, size - ind - 1);
        posts[--size] = null;
        return true;
    }

    @Override
    public boolean updatePost(int postId, String newContent) {
        Post post = getPostById(postId);
        if(post != null) {
            post.setContent(newContent);
            return true;
        }
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        int ind = findIndUsingPredicate((o) -> o.getPostId() == postId);
        if(ind != -1)
            return posts[ind];
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        return findPostsUsingPredicate((o) -> o.getAuthor().equals(author));
    }

    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        return findPostsUsingPredicate(o -> o.getAuthor().equals(author) && dateFrom.isBefore(o.getDate().toLocalDate()) && dateTo.isAfter(o.getDate().toLocalDate()));
    }

    @Override
    public int size() {
        return size;
    }
    private int findIndUsingPredicate( Predicate<Post> predicate){
        for (int i = 0; i < size; i++) {
            if(predicate.test(posts[i])){
                return i;
            }
        }
        return -1;
    }

    private Post[]  findPostsUsingPredicate(Predicate<Post> predicate){
        int count  = 0;
        for (int i = 0; i < size; i++) {
            if(predicate.test(posts[i]))
                count++;
        }
        Post[] res = new Post[count];
        int ind = 0;
        for (int i = 0; i < size; i++) {
            if(predicate.test(posts[i])){
                res[ind] = posts[i];
                ind++;
            }
        }
        return res;
    }

    public void printPosts(){
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }
}
