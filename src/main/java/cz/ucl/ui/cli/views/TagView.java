package cz.ucl.ui.cli.views;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.ui.definition.views.ITagView;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TagView implements ITagView {
    @Override
    public String formatTagList(ITag[] tagList) {
        StringBuilder sb = new StringBuilder("Tagy jsou ve formátu \"NÁZEV_TAGU (ID_TAGU)\"\nSeznam uložených tagů: ");

        sb.append(
                Arrays.stream(tagList).map(this::formatTag).collect(Collectors.joining(", "))
        );

        return sb.toString();
    }

    @Override
    public String formatTag(ITag tag) {
        StringBuilder sb = new StringBuilder("Detail tagu:\n");
        sb.append("ID: ");
        sb.append(tag.getId());
        sb.append("\n");
        sb.append("Název: ");
        sb.append(tag.getTitle());
        sb.append("\n");
        sb.append("Barva: ");
        sb.append(tag.getColor());

        return sb.toString();
    }
}
