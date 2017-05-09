package be.ehb.notedroid4.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by MVH on 21-4-2017.
 */

public class Note implements Parcelable, Comparable<Note> {

    //attributes in tabel = variabelen in klasse
    private long id;
    private String title;
    private String content;
    private String publishDate;
    private String lastModifiedDate;

    //constructors
    public Note() {
    }

    public Note(String title, String content, String publishDate) {
        super();
        this.title = title;
        this.content = content;

        this.publishDate = publishDate;
        this.lastModifiedDate = publishDate;
    }

    private Note(long id, String title, String content, String publishDate, String lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    //getters en setters
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getPublishDate() {
        return publishDate;
    }
    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(publishDate);
        dest.writeString(lastModifiedDate);
    }

    //nodig voor Parcelable -> geen keus, niets aan methodenamen/parameters aanpassen!!!!!!!!!
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        //parcel omzetten naar Note
        public Note createFromParcel(Parcel source)
        {
            //inlezen uit parcel
            Note readNote = new Note(
                    source.readLong(), //read id
                    source.readString(), //read title
                    source.readString(), //read content
                    source.readString(), //read publish date
                    source.readString() //read lastmodified date
            );

            return readNote;
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (id != note.id) return false;
        if (content != null ? !content.equals(note.content) : note.content != null) return false;
        if (title != null ? !title.equals(note.title) : note.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }


    public static Comparator<Note> AscendingTitleComparator = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {

            String noteTitle1 = o1.getTitle().toUpperCase();
            String noteTitle2 = o2.getTitle().toUpperCase();

            //ascending order
            return noteTitle1.compareTo(noteTitle2);
        }
    };

    public static Comparator<Note> DescendingTitleComparator = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {

            String noteTitle1 = o1.getTitle().toUpperCase();
            String noteTitle2 = o2.getTitle().toUpperCase();

            //descending order
            return noteTitle2.compareTo(noteTitle1);
        }
    };

    public static Comparator<Note> AscendingDateComparator = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {

            String noteDate1 = o1.getLastModifiedDate();
            String noteDate2 = o2.getLastModifiedDate();

            //descending order
            return noteDate1.compareTo(noteDate2);
        }
    };

    public static Comparator<Note> DescendingDateComparator = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {

            String noteDate1 = o1.getLastModifiedDate();
            String noteDate2 = o2.getLastModifiedDate();

            //descending order
            return noteDate2.compareTo(noteDate1);
        }
    };

    @Override
    public int compareTo(@NonNull Note o) {
        return 0;
    }
}