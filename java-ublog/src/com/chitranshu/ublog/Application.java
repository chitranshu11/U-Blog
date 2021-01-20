package com.chitranshu.ublog;

import com.chitranshu.ublog.dtos.Post;
import com.chitranshu.ublog.dtos.User;
import com.chitranshu.ublog.exceptions.PostNotFoundException;
import com.chitranshu.ublog.services.PostService;
import com.chitranshu.ublog.services.ServiceFactory;
import com.chitranshu.ublog.services.UserService;
import com.chitranshu.ublog.utils.DateTimeFormatter;
import com.chitranshu.ublog.utils.LogWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Application {
    private final Scanner scanner;

    private final PostService postService;
    private final UserService userService;

    private boolean isLoggedIn;
    private String loggedInEmailId;

    public Application(PostService postService, UserService userService) {
        scanner = new Scanner(System.in);
        this.postService = postService;
        this.userService = userService;
        isLoggedIn = false;
        loggedInEmailId = null;
    }

    private void start() {
        boolean flag = true;

        System.out.println("*********************");
        System.out.println("********U-Blog*******");
        System.out.println("*********************");

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Create Post");
            System.out.println("4. Search Post");
            System.out.println("5. Delete Post");
            System.out.println("6. Filter Post");
            System.out.println("7. Logout");
            System.out.println("8. Exit");

            System.out.print("\nPlease select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    createPost();
                    break;
                case "4":
                    searchPost();
                    break;
                case "5":
                    deletePost();
                    break;
                case "6":
                    filterPost();
                    break;
                case "7":
                    logout();
                    break;
                case "8":
                    flag = false;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        } while (flag);
    }

    private void login() {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("********Login********");
        System.out.println("*********************");

        User user = new User();

        System.out.println("Enter EmailId");
        user.setEmailId(scanner.nextLine());
        System.out.println("Enter Password");
        user.setPassword(scanner.nextLine());
        try {
            userService.login(user);
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInEmailId = user.getEmailId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void register() {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Register*******");
        System.out.println("*********************");

        User user = new User();

        System.out.println("Enter EmailId:");
        user.setEmailId(scanner.nextLine());
        System.out.println("Enter Password");
        user.setPassword(scanner.nextLine());

        try {
            userService.register(user);
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInEmailId = user.getEmailId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void createPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Create Post*****");
        System.out.println("*********************");

        Post post = new Post();

        post.setEmailId(loggedInEmailId);

        System.out.println("Enter tile:");
        post.setTitle(scanner.nextLine());

        System.out.println("Enter tag:");
        post.setTag(scanner.nextLine());


        System.out.println("Enter description:");
        post.setDescription(scanner.nextLine());

        post.setTimestamp(LocalDateTime.now());

        try {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        postService.create(post);
                        System.out.println("Post created successfully");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    //Writing Log Message
                    String message = DateTimeFormatter.format(post.getTimestamp()) + "\t" +
                            "New post with title " + post.getTitle() +
                            " created by " + post.getEmailId();
                    String path = System.getProperty("user.dir");
                    LogWriter.writeLog(message, path);
                }
            };
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Search Post*****");
        System.out.println("*********************");

        List<Post> posts;
        try {
            posts = postService.getPostsByEmailId(loggedInEmailId);
            if (posts == null || posts.size() == 0)
                System.out.println("Sorry no posts exists for this email id");
            else {
                for (Post post : posts)
                    postService.printPost(post);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deletePost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Delete Post*****");
        System.out.println("*********************");

        System.out.println("Enter post id:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            if (postService.deletePost(id, loggedInEmailId))
                System.out.println("Post deleted successfully!");
            else
                System.out.println("You are not authorised to delete this post");
        } catch (IOException | NumberFormatException exception) {
            System.out.println("You have not entered number value");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterPost() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*****Filter Post*****");
        System.out.println("*********************");

        Set<String> tags;
        List<Post> posts;
        try {
            tags = postService.getAllTags();
            System.out.println(tags);

            System.out.println("Enter tag to apply filter:");
            String tag = scanner.nextLine();

            posts = postService.getPostsByTag(tag);
            if (posts == null || posts.size() == 0)
                throw new PostNotFoundException("Sorry no posts exists for this tag");

            for (Post post : posts) postService.printPost(post);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void logout() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Logged out successfully");
        isLoggedIn = false;
        loggedInEmailId = null;
    }

    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.getUserService();
        PostService postService = serviceFactory.getPostService();
        Application application = new Application(postService, userService);
        application.start();
    }
}
