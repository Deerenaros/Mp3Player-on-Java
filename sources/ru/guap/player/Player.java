package ru.guap.player;

import ru.guap.player.controller.*;
import ru.guap.player.model.*;
import ru.guap.player.view.*;

class Player {
	public Player () {
		Controller control =
			new Controller (
				new View (),
				new Model ()
			);
	}
}