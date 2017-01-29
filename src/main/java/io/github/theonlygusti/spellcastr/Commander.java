/*
 *  This file is part of SpellCastr.
 *
 *  SpellCastr is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  SpellCastr is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SpellCastr  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.theonlygusti.spellcastr;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class Commander implements CommandExecutor {
  private final Plugin plugin;

  private JCommander jcommander;
  private CommandTemplate template;


  @Parameters(commandDescription = "Bind a spell to an item.")
  private class CommandBind {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Create a new spell or item.")
  private class CommandCreate {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Set a description for a spell or item.")
  private class CommandSetLore {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Set the description for the item or spell.")
  private class CommandSetLore {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  private class CommandTemplate {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  public String help() {
    StringBuilder help = new StringBuilder();
    jcommander.usage(help);
    return help.toString();
  }

  public Commander(Plugin plugin) {
    template = new CommandTemplate();
    jcommander = new JCommander(template);
    jcommander.setProgramName("spellcastr");
    CommandBind bind = new CommandBind();
    jcommander.addCommand("bind", bind);
    CommandCreate create = new CommandCreate();
    jcommander.addCommand("create", create);
    CommandSetLore setLore = new CommandSetLore();
    jcommander.addCommand("setlore", setLore);

    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    try {
      jcommander.parse(args);
    } catch(Exception exception) {
      sender.sendMessage(help());
    }

    return true;
  }
}
