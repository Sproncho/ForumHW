package telran.forum.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.forum.model.ForumImpl;
import telran.forum.model.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ForumImplTest {
    Post[] posts;
    ForumImpl forum;
    @BeforeEach
    void setUp(){
        forum = new ForumImpl(13);
        posts = new Post[]{
                new Post("misha",11,"Mypost1","yababays1"),
                new Post("sahsha",13,"Mypost3","yababays3"),
                new Post("kolya",14,"Mypost4","yababays4"),
                new Post("pasha",12,"Mypost2","yababays2"),
                new Post("misha",16,"Mypost6","yababays6"),
                new Post("added",15,"Mypost5","yababays5")
        };
        posts[0].setDate(LocalDateTime.of(LocalDate.of(2022,01,21), LocalTime.MAX));
        posts[1].setDate(LocalDateTime.of(LocalDate.of(2022,03,21), LocalTime.MAX));
        posts[2].setDate(LocalDateTime.of(LocalDate.of(2022,04,21), LocalTime.MAX));
        posts[3].setDate(LocalDateTime.of(LocalDate.of(2022,02,21), LocalTime.MAX));
        posts[4].setDate(LocalDateTime.of(LocalDate.of(2022,05,21), LocalTime.MAX));
        for (int i = 0; i < posts.length - 1; i++) {
            forum.addPost(posts[i]);
        }
    }

    @Test
    void testAddPost(){
        int size = forum.size();
        boolean res = forum.addPost(posts[5]);
        assertTrue(res);
        assertEquals(posts[5],forum.getPostById(15));
        assertEquals(size + 1, forum.size());
        forum.printPosts();
    }
    @Test
    void testRemovePost(){
        boolean res = forum.removePost(12);
        assertTrue(res);
        assertNull(forum.getPostById(12));
    }
    @Test
    void testUpdatePost(){
        boolean res = forum.updatePost(12,"abacaba");
        assertTrue(res);
        assertEquals("abacaba", forum.getPostById(12).getContent());
    }

    @Test
    void testGetPostById(){
        Post post = forum.getPostById(12);
        assertEquals(posts[3],post);
    }

    @Test
    void testGetPostsByAuthor(){
        Post[] expected1 = new Post[]{
                new Post("misha",11,"Mypost1","yababays1"),
                new Post("misha",16,"Mypost6","yababays6")
        };
        assertArrayEquals(expected1,forum.getPostsByAuthor("misha"));
        Post[] expected2 = new Post[]{
                new Post("misha",11,"Mypost1","yababays1"),
        };
        assertArrayEquals(expected2, forum.getPostsByAuthor("misha",LocalDate.of(2020,01,01),LocalDate.of(2022,03,01)));
    }
}