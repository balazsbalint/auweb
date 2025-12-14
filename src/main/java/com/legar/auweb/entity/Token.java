package com.legar.auweb.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bálint Balázs
 */
@Entity
public class Token implements Serializable {

  public static final int TEXT_LENGTH = 255;

  private Long id;
  private int type;
  private String text;
  private String source;
  private int line;
  private int column;
  private String typeName;
  private Token parent;
  private List<Token> children;
  private int version;

  @Id
  @GeneratedValue
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(insertable = false, updatable = false, name = "PARENT")
  public Token getParent() {
    return parent;
  }

  public void setParent(Token parent) {
    this.parent = parent;
  }

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT")
  @OrderColumn(name = "SUCCESSION")
  public List<Token> getChildren() {
    return children;
  }

  public void setChildren(List<Token> children) {
    this.children = children;
  }

  @Column(name = "TYPE", nullable = false)
  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Lob
  @Column(name = "TEXT")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Lob
  @Column(name = "SOURCE")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Column(name = "LINE", nullable = false)
  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  @Column(name = "COLUMN_OF_LINE", nullable = false)
  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  @Basic
  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Version
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public int hashCode() {
    return id == null ? System.identityHashCode(this) : id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (id == null) {
      return this == obj;
    }
    if (!(obj instanceof Token)) {
      return false;
    }
    return id.equals(((Token) obj).getId());
  }
}
