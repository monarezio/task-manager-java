package cz.ucl.logic.data.managers;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.data.dao.*;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.definitions.IUserManager;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.IUserDAOToUserMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IUserToUserDAOMapper;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidCredentialsException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserManager implements IUserManager {

    private final IUserDAOToUserMapper userDAOToUserMapper;
    private final IHibernateSessionFactory hibernateUtil;
    private final IUserToUserDAOMapper userToUserDAOMapper;

    public UserManager(IUserDAOToUserMapper userDAOToUserMapper, IUserToUserDAOMapper userToUserDAOMapper, IHibernateSessionFactory hibernateUtil) {
        this.userDAOToUserMapper = userDAOToUserMapper;
        this.userToUserDAOMapper = userToUserDAOMapper;
        this.hibernateUtil = hibernateUtil;
    }

    public IUser getByEmail(String email) throws InvalidCredentialsException {
        try {
            AtomicReference<UserDAO> result = new AtomicReference<>();
            hibernateUtil.createSession(i -> result.set(i.createQuery("from UserDAO where email LIKE ?0", UserDAO.class)
                    .setParameter(0, email)
                    .getSingleResult()
            ));
            return userDAOToUserMapper.mapOrNull(result.get());
        } catch (NoResultException e) {
            throw new InvalidCredentialsException("Invalid combination");
        }
    }

    public void addUser(IUser user) throws EmailAddressAlreadyUsedException {
        try {
            hibernateUtil.createSession(i -> {
                UserDAO newUser = userToUserDAOMapper.mapOrNull(user);
                i.save(newUser);

                TagDAO tagUCL = new TagDAO();
                tagUCL.setTitle("UCL");
                tagUCL.setColor(ColorDAO.GREEN);
                tagUCL.setUser(newUser);
                i.save(tagUCL);

                TagDAO tagJSE = new TagDAO();
                tagJSE.setTitle("JSE");
                tagJSE.setColor(ColorDAO.RED);
                tagJSE.setUser(newUser);
                i.save(tagJSE);

                TagDAO tagWEB = new TagDAO();
                tagWEB.setTitle("WEB");
                tagWEB.setColor(ColorDAO.BLUE);
                tagWEB.setUser(newUser);
                i.save(tagWEB);

                TagDAO tag3DT = new TagDAO();
                tag3DT.setTitle("3DT");
                tag3DT.setColor(ColorDAO.BLACK);
                tag3DT.setUser(newUser);
                i.save(tag3DT);

                TagDAO tagPR1 = new TagDAO();
                tagPR1.setTitle("PR1");
                tagPR1.setColor(ColorDAO.GREEN);
                tagPR1.setUser(newUser);
                i.save(tagPR1);

                TagDAO tagPES = new TagDAO();
                tagPES.setTitle("PES");
                tagPES.setColor(ColorDAO.RED);
                tagPES.setUser(newUser);
                i.save(tagPES);

                TagDAO tagNakupy = new TagDAO();
                tagNakupy.setTitle("Nákupy");
                tagNakupy.setColor(ColorDAO.GREEN);
                tagNakupy.setUser(newUser);
                i.save(tagNakupy);

                TagDAO tagWishlist = new TagDAO();
                tagWishlist.setTitle("Wishlist");
                tagWishlist.setColor(ColorDAO.BLUE);
                tagWishlist.setUser(newUser);
                i.save(tagWishlist);

                CategoryDAO categoryPersonal = new CategoryDAO();
                categoryPersonal.setTitle("Osobní");
                categoryPersonal.setColor(ColorDAO.RED);
                categoryPersonal.setUser(newUser);
                i.save(categoryPersonal);

                CategoryDAO categorySchool = new CategoryDAO();
                categorySchool.setTitle("Škola");
                categorySchool.setColor(ColorDAO.GREEN);
                categorySchool.setUser(newUser);
                i.save(categorySchool);

                CategoryDAO categoryWork = new CategoryDAO();
                categoryWork.setTitle("Work");
                categoryWork.setColor(ColorDAO.BLACK);
                categoryWork.setUser(newUser);
                i.save(categoryWork);

                TaskDAO task1 = new TaskDAO();
                task1.setTitle("Toto je jednoduchý úkol");
                task1.setDone(false);
                task1.setNote("");
                task1.setUser(newUser);
                i.save(task1);

                TaskDAO task2 = new TaskDAO();
                task2.setTitle("Toto je už dokončený úkol");
                task2.setNote("");
                task2.setDone(true);
                task2.setUser(newUser);
                i.save(task2);

                TaskDAO task3 = new TaskDAO();
                task3.setTitle("Nakoupit na večeři");
                task3.setNote("");
                task3.setDone(false);
                task3.setCategory(categoryPersonal);
                task3.setTags(Collections.singletonList(tagNakupy));
                task3.setUser(newUser);
                i.save(task3);

                TaskDAO task4 = new TaskDAO();
                task4.setTitle("Udělat semestrální práci z předmětu WEB");
                task4.setDone(false);
                task4.setNote("");
                task4.setCategory(categorySchool);
                task4.setTags(Arrays.asList(tagUCL, tagWEB));
                task4.setUser(newUser);
                i.save(task4);
            });
        } catch (PersistenceException e) {
            throw new EmailAddressAlreadyUsedException("Email " + user.getEmail() + " already in use!");
        }
    }

    private int randomNumberGenerator(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min); //TODO: If more time this should be in util class
    }

    @Override
    public void addUserWithoutMockData(IUser user) throws EmailAddressAlreadyUsedException {
        try { //TODO
            hibernateUtil.createSession(s -> {
                UserDAO newUser = userToUserDAOMapper.mapOrNull(user);
                s.save(newUser);


                ColorDAO[] colors = ColorDAO.values();
                TagDAO[] tags = new TagDAO[50];
                for (int i = 0; i < tags.length; i++) { //Tags
                    String tagTitle = randomNumberGenerator(0, 1000) + "";
                    ColorDAO tagColor = colors[(new Random()).nextInt(colors.length)];
                    tags[i] = new TagDAO();
                    tags[i].setUser(newUser);
                    tags[i].setTitle(tagTitle);
                    tags[i].setColor(tagColor);
                    s.save(tags[i]);
                }

                CategoryDAO[] categeories = new CategoryDAO[20];
                for (int i = 0; i < categeories.length; i++) {
                    String categoryTitle = randomNumberGenerator(0, 1000) + "";
                    ColorDAO categoryColor = colors[(new Random()).nextInt(colors.length)];
                    categeories[i] = new CategoryDAO();
                    categeories[i].setUser(newUser);
                    categeories[i].setTitle(categoryTitle);
                    categeories[i].setColor(categoryColor);
                    s.save(categeories[i]);

                    for (int j = 0; j < 20; j++) {
                        String titleName = randomNumberGenerator(0, 1000) + "";
                        String note = randomNumberGenerator(0, 1000) + "";

                        TaskDAO taskDAO = new TaskDAO();
                        taskDAO.setUser(newUser);
                        taskDAO.setNote(note);
                        taskDAO.setTitle(titleName);
                        taskDAO.setDone(randomNumberGenerator(0, 2) == 1);
                        if(randomNumberGenerator(0, 10) == 1)
                            taskDAO.setCategory(categeories[i]);

                        int tagAmount = randomNumberGenerator(0, 10);
                        List<Long> addedTags = new ArrayList<>();

                        while(addedTags.size() < tagAmount) {
                            TagDAO randomTag = tags[(new Random()).nextInt(tags.length)];
                            if(!addedTags.contains(randomTag.getId())) {
                                addedTags.add(randomTag.getId());
                            }
                        }

                        taskDAO.setTags(Arrays.asList(addedTags.stream().map(TagDAO::new).toArray(TagDAO[]::new)));
                        s.save(taskDAO);
                    }
                }
            });
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new EmailAddressAlreadyUsedException("Email " + user.getEmail() + " already in use!");
        }
    }

    @Override
    public void updateUser(long userId, String username, String email) throws EmailAddressAlreadyUsedException {
        try {
            hibernateUtil.createSession(s -> {
                UserDAO user = s.get(UserDAO.class, userId);
                user.setUsername(username);
                user.setEmail(email);

                s.update(user);
            });
        } catch (PersistenceException e) {
            throw new EmailAddressAlreadyUsedException("Email " + email + " already in use!");
        }
    }

    @Override
    public void updatePassword(long userId, String password) {
        hibernateUtil.createSession(s -> {
            UserDAO user = s.get(UserDAO.class, userId);
            user.setPassword(password);
            s.update(user);
        });
    }

    @Override
    public void destroy(long userId) {
        hibernateUtil.createSession(s -> {
            UserDAO user = s.get(UserDAO.class, userId);
            s.delete(user);
        });
    }
}
