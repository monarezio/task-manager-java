package cz.ucl.logic.data.mappers.DAOToEntity;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IColorDAOToColorMapper;
import cz.ucl.logic.data.dao.ColorDAO;

final public class ColorDAOToColorMapper implements IColorDAOToColorMapper {

    public static final IColorDAOToColorMapper instance = new ColorDAOToColorMapper();

    private ColorDAOToColorMapper() {
    }

    /**
     * @param v
     * @return if `v` null returns null
     */
    @Override
    public Color mapOrNull(ColorDAO v) {
        if (v == null) return null;

        return Color.valueOf(v.toString());
    }
}
