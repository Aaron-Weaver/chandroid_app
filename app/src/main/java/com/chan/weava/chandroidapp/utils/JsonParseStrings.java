package com.chan.weava.chandroidapp.utils;

/**
 * JSON Parse Strings
 *
 * Class containing various keywords for JSON parsing.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/20/14
 */
public class JsonParseStrings
{
    // Parse identifier for arrays
    public static final String JSON_ARRAY_PARSE_BOARDS = "boards";
    public static final String JSON_ARRAY_PARSE_THREADS = "threads";
    public static final String JSON_ARRAY_PARSE_POSTS = "posts";

    // Parse identifier for array elements of boards
    public static final String TITLE = "title";
    public static final String LINK = "board";

    // Parse identifier for array elements of threads
    public static final String COMMENT = "com";
    public static final String OMITTED_POSTS = "omitted_posts";
    public static final String OMITTED_IMAGES = "omitted_images";
    public static final String RESTO = "resto";
    public static final String POST_NUMBER = "no";
    public static final String REPLIES = "replies";
    public static final String NAME = "name";
    public static final String IMAGES = "images";
    public static final String SUBJECT = "sub";
    public static final String TRIPCODE = "trip";
    public static final String WORKSAFE = "ws_board";

    // Parse identifiers for images
    public static final String RENAMED_IMAGE = "tim";
    public static final String IMAGE_FILE_EXTENSION = "ext";
    public static final String IMAGE_NAME = "filename";
    public static final String UNIX_TIMESTAMP = "time";
    public static final String THUMB_WIDTH = "tn_w";
    public static final String THUMB_HEIGHT = "tn_h";
}
