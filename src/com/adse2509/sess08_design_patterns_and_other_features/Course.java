package com.adse2509.sess08_design_patterns_and_other_features;

/**
 * Represents a course with a title, score, and unique course code.
 *
 * @author Nyanjui
 */
public class Course
{
    /** The title of the course. */
    private String title;

    /** The score achieved for the course. */
    private int score;

    /** The unique code identifying the course. */
    private long courseCode;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public long getCourseCode()
    {
        return courseCode;
    }

    public void setCourseCode(long courseCode)
    {
        this.courseCode = courseCode;
    }
}
