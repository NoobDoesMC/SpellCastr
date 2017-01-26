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

  public Commander(Plugin plugin) {
    this.plugin = plugin;
  }

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

  private class CommandTemplate {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    CommandTemplate template = new CommandTemplate();
    JCommander jcommander = new JCommander(template);
    jcommander.setProgramName("spellcastr");

    CommandBind bind = new CommandBind();
    jcommander.addCommand("bind", bind);
    CommandCreate create = new CommandCreate();
    jcommander.addCommand("create", create);

    try {
      jcommander.parse(args);
    } catch(Exception exception) {
      StringBuilder help = new StringBuilder();
      jcommander.usage(help);
      sender.sendMessage(help.toString());
    }

    return true;
  }
}
