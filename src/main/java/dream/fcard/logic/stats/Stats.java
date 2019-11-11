//@@author nattanyz
package dream.fcard.logic.stats;

import java.util.logging.Logger;

import dream.fcard.core.commons.core.LogsCenter;

/** Abstract class for statistics objects, like UserStats and DeckStats. */
public abstract class Stats {

    /** Logger for statistics-related issues. */
    protected Logger logger = LogsCenter.getLogger(Stats.class);

    /** Starts a new Session, representing the current Session the user is engaging in. */
    public abstract void startCurrentSession();

    /** Ends the current Session the user is engaging in. */
    public abstract void endCurrentSession();

    /** Gets the current session. */
    public abstract Session getCurrentSession();
}
