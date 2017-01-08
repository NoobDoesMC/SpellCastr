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

import java.util.List;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class Commander implements CommandExecutor {
  private final Plugin plugin;

  public Commander(Plugin plugin) {
    this.plugin = plugin;
  }

  private class CommandTemplate {
    @Parameter
    private List<String> parameters = new ArrayList<>();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    CommandTemplate template = new CommandTemplate();
    new JCommander(template, args);

    for (String parameter : template.parameters)
      sender.sendMessage(label);
    return true;
  }
}
