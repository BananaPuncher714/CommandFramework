package io.github.bananapuncher714.commandframework;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import io.github.bananapuncher714.commandframework.api.CommandParameters;
import io.github.bananapuncher714.commandframework.api.SubCommand;
import io.github.bananapuncher714.commandframework.api.executor.CommandExecutableMessage;
import io.github.bananapuncher714.commandframework.api.validator.sender.SenderValidatorPlayer;

/**
 * "banana could you create a repo for this or something" - Offz, Lord of Kotlin and NMS
 * Fine, I have no idea what you'd use this for though. :P
 * 
 * @author BananaPuncher714
 */
public class Demo {
	public static void main( String[] params ) {
		CommandSender sender = new DummySender();

		MockCommand[] commands = new MockCommand[] {
				new MockCommand( "main" ),
				new MockCommand( "main", "su" ),
				new MockCommand( "main", "sub" ),
				new MockCommand( "main", "sub1" ),
				new MockCommand( "main", "sub2" ),
				new MockCommand( "main", "sub2", "subofsub2" ),
				new MockCommand( "main", "sub2", "invalidsub" ),
		};
		
		SubCommand command = new SubCommand( "main" )
			    .add( new SubCommand( "sub1" )
			    		.defaultTo( new CommandExecutableMessage( "Subcommand 1 ran!" ) ) )
			    .add( new SubCommand( "sub2" )
			    		.addSenderValidator( new SenderValidatorPlayer() )
			    		.defaultTo( new CommandExecutableMessage( "Subcommand 2 ran as a player!" ) ) )
			    .add( new SubCommand( "sub2" )
			    		.add( new SubCommand( "subofsub2" )
			    				.defaultTo( new CommandExecutableMessage( "Subcommand of subcommand 2 ran!" ) ) )
			    		.defaultTo( new CommandExecutableMessage( "Subcommand 2 ran!" ) ) )
			    .whenUnknown( new CommandExecutableMessage( "Invalid Argument!" ) )
			    .defaultTo( new CommandExecutableMessage( "You provided no arguments!" ) );
		
		// Testing
		for ( MockCommand cmd : commands ) {
			System.out.println( "Testing command: '" + cmd.toString() + "'" );
			System.out.println( "Output:" );
			command.submit( sender, cmd.getCmd(), cmd.getArgs(), new CommandParameters() ).execute( sender );
			System.out.println( "Tab complete:" );
			Collection< String > options = command.getTabCompletions( sender, cmd.getArgs() );
			StringBuilder builder = new StringBuilder();
			for ( String str : options ) {
				builder.append( "'" );
				builder.append( str );
				builder.append( "' " );
			}
			System.out.println( builder.toString() );
			System.out.println();
		}
	}

	private static final class DummySender implements CommandSender {
		private Set< String > permissions = new HashSet< String >();

		@Override
		public PermissionAttachment addAttachment(Plugin arg0) {
			return null;
		}

		@Override
		public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
			return null;
		}

		@Override
		public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
			return null;
		}

		@Override
		public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
			return null;
		}

		@Override
		public Set<PermissionAttachmentInfo> getEffectivePermissions() {
			return null;
		}

		@Override
		public boolean hasPermission(String arg0) {
			return permissions.contains( arg0 );
		}

		public void addPermission( String permission ) {
			permissions.add( permission );
		}

		@Override
		public boolean hasPermission(Permission arg0) {
			return false;
		}

		@Override
		public boolean isPermissionSet(String arg0) {
			return false;
		}

		@Override
		public boolean isPermissionSet(Permission arg0) {
			return false;
		}

		@Override
		public void recalculatePermissions() {
		}

		@Override
		public void removeAttachment(PermissionAttachment arg0) {
		}

		@Override
		public boolean isOp() {
			return false;
		}

		@Override
		public void setOp( boolean arg0 ) {
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		public Server getServer() {
			return null;
		}

		@Override
		public void sendMessage(String arg0) {
			System.out.println( "Message: " + arg0 );

		}

		@Override
		public void sendMessage(String[] arg0) {
		}

		@Override
		public Spigot spigot() {
			return null;
		}
	}
}