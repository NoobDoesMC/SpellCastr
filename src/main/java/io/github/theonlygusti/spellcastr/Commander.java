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

  @Parameters(commandDescription = "Set the spell's type.")
  private class CommandSetType {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Set the spell's type.")
  private class CommandSetOption {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Specify the vanilla item that this item is represented by.")
  private class CommandSetItem {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Specify the crafting recipe that can be used at a crafting table to obtain this item.")
  private class CommandCraft {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Parameters(commandDescription = "Display this help message, or specify a subcommand to get help on.")
  private class CommandHelp {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  private static class CommandTemplate {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  private JCommander jcommander;
  private CommandBind bind;
  private CommandCreate create;
  private CommandSetLore setLore;
  private CommandSetItem setItem;
  private CommandSetOption setOption;
  private CommandCraft craft;
  private CommandSetType setType;
  private CommandHelp help;
  private CommandTemplate template;

  public Commander(Plugin plugin) {
    template = new CommandTemplate();
    jcommander = new JCommander(template);
    jcommander.setProgramName("spellcastr");
    bind = new CommandBind();
    jcommander.addCommand("bind", bind);
    create = new CommandCreate();
    jcommander.addCommand("create", create);
    setLore = new CommandSetLore();
    jcommander.addCommand("lore", setLore);
    setItem = new CommandSetItem();
    jcommander.addCommand("item", setItem);
    craft = new CommandCraft();
    jcommander.addCommand("craft", craft);
    setType = new CommandSetType();
    jcommander.addCommand("type", setType);
    setOption = new CommandSetOption();
    jcommander.addCommand("option", setOption);
    help = new CommandHelp();
    jcommander.addCommand("help", help);

    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    try {
      jcommander.parse(args);

      switch (jcommander.getParsedCommand()) {
        case "create":
          return createNewSpellOrItem(sender, create);
        case "help":
          return sendHelpMessage(sender, null);
        default:
          sender.sendMessage(jcommander.getParsedCommand());
      }
    } catch(Exception exception) {
      return false;
    }
    return true;
  }

  public boolean sendHelpMessage(CommandSender sender, String command) {
    try {
      StringBuilder helpMessage = new StringBuilder();
      jcommander.usage(helpMessage);
      sender.sendMessage(helpMessage.toString());
      return true;
    } catch (Exception exception) {
      plugin.getServer().getLogger().info("Something went wrong sending a help message.");
      return false;
    }
  }

  public boolean createNewSpellOrItem(CommandSender sender, CommandCreate options) {
    if (parameters.size() < 2) {
      return sendHelpMessage(sender, "create");
    }
    return false;
  }
}
