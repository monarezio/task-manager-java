package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.data.dao.ColorDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;

final public class ColorToColorDAOMapper implements IColorToColorDAOMapper {

    public static IColorToColorDAOMapper instance = new ColorToColorDAOMapper();

    private ColorToColorDAOMapper() {
    }

    @Override
    public ColorDAO mapOrNull(Color v) {
        if (v == null) return null;
        return ColorDAO.valueOf(v.toString());
    }
}
