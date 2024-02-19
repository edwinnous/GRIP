package nl.overheid.digv.shared;

/**
 * A convenient enumeration of example end-points in RIVMweb.
 */
public enum EndPoint {
  TOKEN("/session/token"),
  AUTHOR("/api/author");

  public final String name;

  private EndPoint(final String name) {
    this.name = name;
  }

  /**
   * @return The path associated with the end-point.
   */
  public final String getPath() {
    return name;
  }

  /**
   * @return The full end-point path including the given query string.
   */
  public final String getPath(final String query) {
    return name + "/" + query;
  }

}
