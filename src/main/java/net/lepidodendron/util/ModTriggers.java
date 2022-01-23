package net.lepidodendron.util;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModTriggers

{
    public static final CustomTrigger ENTER_PRECAMBRIAN = new CustomTrigger("enter_pf_precambrian");
    public static final CustomTrigger ENTER_CAMBRIAN = new CustomTrigger("enter_pf_cambrian");
    public static final CustomTrigger ENTER_ORDOVICIAN_SILURIAN = new CustomTrigger("enter_pf_ordovician_silurian");
    public static final CustomTrigger ENTER_DEVONIAN = new CustomTrigger("enter_pf_devonian");
    public static final CustomTrigger ENTER_CARBONIFEROUS = new CustomTrigger("enter_pf_carboniferous");
    public static final CustomTrigger ENTER_PERMIAN = new CustomTrigger("enter_pf_permian");
    public static final CustomTrigger ENTER_TRIASSIC = new CustomTrigger("enter_pf_triassic");
    public static final CustomTrigger ENTER_JURASSIC = new CustomTrigger("enter_pf_jurassic");
    public static final CustomTrigger REJUVENATE = new CustomTrigger("pf_rejuvenate");

    /*
     * This array just makes it convenient to register all the criteria.
     */
    public static final CustomTrigger[] TRIGGER_ARRAY = new CustomTrigger[] {
            ENTER_PRECAMBRIAN,
            ENTER_CAMBRIAN,
            ENTER_ORDOVICIAN_SILURIAN,
            ENTER_DEVONIAN,
            ENTER_CARBONIFEROUS,
            ENTER_PERMIAN,
            ENTER_TRIASSIC,
            ENTER_JURASSIC,
            REJUVENATE
    };


    /**
     * This method is part of my simple custom advancement triggering tutorial.
     * See: http://jabelarminecraft.blogspot.com/p/minecraft-modding-custom-triggers-aka.html
     */
    public static void registerTriggers()
    {
        // DEBUG
        System.out.println("Registering custom triggers");

        Method method;

        method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);

        method.setAccessible(true);

        for (int i=0; i < TRIGGER_ARRAY.length; i++)
        {
            try
            {
                method.invoke(null, TRIGGER_ARRAY[i]);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }
    }
}