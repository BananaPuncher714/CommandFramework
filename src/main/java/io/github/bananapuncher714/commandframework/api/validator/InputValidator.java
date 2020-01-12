package io.github.bananapuncher714.commandframework.api.validator;

import java.util.Collection;

/**
 * "GENERICS ARE TRASH
 *  T.R.A.S.H." - Derongan, Lord of Fiddly Things
 * 
 * @author BananaPuncher714
 *
 * @param < T >
 * Anything that can be constructed from string through user input.
 */
public interface InputValidator< T > {
	Collection< String > getTabCompletes();
	boolean isValid( String input, String[] args );
	T get( String input );
}
