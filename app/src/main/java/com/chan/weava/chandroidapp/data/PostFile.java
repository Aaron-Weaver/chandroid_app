package com.chan.weava.chandroidapp.data;

/**
 * PostFile
 *
 * This class holds data on files associated with posts.
 *
 * Data like filename and file size that are associated with post images and webms.
 * Data gathered from the 4chan API.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v[Insert Version Number]
 * @since 9/10/14
 */
public abstract class PostFile
{
    private String mFilename;
    private String mFileExtension;
    private String mFileSize;
    private String mRenamedImageFilename;
    private int mImageHeight;
    private int mImageWidth;
    private int mThumbWidth;
    private int mThumbHeight;

    public String getFilename() { return this.mFilename; }

    public void setFilename(String filename) { this.mFilename = filename; }

    public String getFileExtension(String fileExtension) { return this.mFileExtension; }

    public void setFileExtension(String fileExtension) { this.mFileExtension = fileExtension; }

    public String getFileSize() { return this.mFileSize; }

    public void setFileSize(String fileSize) { this.mFileSize = fileSize; }

    public int getImageHeight() { return this.mImageHeight; }

    public void setImageHeight(int imageHeight) { this.mImageHeight = imageHeight; }

    public int getImageWidth() { return this.mImageWidth; }

    public void setImageWidth(int imageWidth) { this.mImageWidth = imageWidth; }

    public int getThumbWidth() { return this.mThumbWidth; }

    public void setThumbWidth(int thumbWidth) { this.mThumbWidth = thumbWidth; }

    public int getThumbHeight() { return this.mThumbHeight; }

    public void setThumbHeight(int thumbHeight) { this.mThumbHeight = thumbHeight; }

    public String getRenamedImageFilename()
    {
        return mRenamedImageFilename;
    }

    public void setRenamedImageFilename(String mRenamedImageFilename)
    {
        this.mRenamedImageFilename = mRenamedImageFilename;
    }
}
