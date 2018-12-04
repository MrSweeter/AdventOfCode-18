package be.vanga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public abstract class AoCProgram {

	protected List<String> inputs;
	
	// https://adventofcode.com/2018/day/X
	public AoCProgram(File file) throws IOException {
		inputs = Files.readAllLines(file.toPath());
	}
	
	public abstract void puzzle1();
	
	public abstract void puzzle2();
}
