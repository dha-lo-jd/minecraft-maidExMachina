package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.src.RenderQuadVertexHelper.RenderFace;

public class MEMPlanOptionalQuadFrame implements IMEMPlanBlockInfo {

	MEMPlanDefaultFrame defaultFrame;

	Integer[] colors;
	String[] textures;
	RenderFace[] faces = null;

	public void setFaces(RenderFace[] faces) {
		this.faces = faces;
	}

	public MEMPlanOptionalQuadFrame(MEMPlanDefaultFrame defaultFrame,
			Integer[] colors, String[] textures) {
		this.defaultFrame = defaultFrame;
		this.colors = colors;
		this.textures = textures;
	}

	@Override
	public void setInfo(EntityMEMFrame frame) {
		defaultFrame.setInfo(frame);
		for (int i = 0; i < 6; i++) {
			if (colors != null && colors[i] != null) {
				frame.setColor(colors[i], i);
				frame.setFace(RenderFace.NORMAL, i);
			}
			if (textures != null && textures[i] != IMEMPlanBlockInfo.EMPTY) {
				frame.setTexture(textures[i], i);
				frame.setFace(RenderFace.NORMAL, i);
			}
			if (faces != null) {
				frame.setFace(faces[i], i);
			}
		}
	}

	public static <T extends Object> T[] getFaceArray(T value, T defaultValue,
			T[] array, int... face) {
		Set<Integer> faceSet = new HashSet<Integer>();
		for (int i : face) {
			faceSet.add(i);
		}

		List<T> result = new ArrayList<T>();
		for (int i = 0; i < 6; i++) {
			if (faceSet.contains(i)) {
				result.add(value);
			} else {
				result.add(defaultValue);
			}
		}
		return result.toArray(array);
	}

}
