package io.github.bananapuncher714.commandframework.api.validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class InputValidatorString implements InputValidator< String > {
	protected Set< String > values = new HashSet< String >();
	
	public InputValidatorString( String value ) {
		values.add( value.toLowerCase() );
	}

	public InputValidatorString( String... values ) {
		if ( values.length == 0 ) throw new IllegalArgumentException( "Must provide at least 1 argument!" );
		for ( String str : values ) {
			this.values.add( str.toLowerCase() );
		}
	}
	
	@Override
	public Collection< String > getTabCompletes() {
		return values;
	}

	@Override
	public boolean isValid( String input, String[] args ) {
		return values.contains( input.toLowerCase() );
	}

	@Override
	public String get( String input ) {
		return input;
	}
}
