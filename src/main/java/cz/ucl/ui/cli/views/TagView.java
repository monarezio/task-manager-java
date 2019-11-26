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
        return tag.getTitle() + " (" + tag.getId() + ")";
    }
}
