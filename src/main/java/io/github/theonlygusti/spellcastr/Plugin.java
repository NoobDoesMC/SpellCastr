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

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
  public Configuration configuration;

  @Override
  public void onEnable() {
    getLogger().info("Enabled.");
    this.configuration = new Configuration(this);
    this.configuration.loadDefaults();
    this.getCommand("spellcastr").setExecutor(new Commander(this));
  }
}
