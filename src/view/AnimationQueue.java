package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;

/**
 * This class handles multiple animations for a single object. Animations are added to the Animation queue and are played
 * in sequence as each animation finishes. This allows for sequential transitions even if animations are added at different times
 * @author Ryan Bergamini
 */
public class AnimationQueue 
{
	private ArrayList<Animation> animations;
	private Animation currentAnimation;
	
	/**
	 * Creates a new AnimationQueue
	 */
	public AnimationQueue()
	{
		animations = new ArrayList<Animation>();
		currentAnimation = null;
	}
	
	/**
	 * Adds an animation to the queue
	 * @param animation- the Animation to be added to the queue
	 */
	public void addAnimation(Animation anim)
	{
		
		Animation animation = setCallNexAnimationOnFinishOf(anim);

		if(currentAnimation == null || currentAnimation.getStatus() == Status.STOPPED)
		{
			currentAnimation = animation;
			currentAnimation.play();
		}
		else
		{
			animations.add(animation);
		}
	}
	
	/**
	 * Properly adds a nextAnimation() call to the end of animation
	 * @param animation- animation being added to queue
	 * @return animation that calls nextAnimation after completing
	 */
	private Animation setCallNexAnimationOnFinishOf(Animation animation)
	{
		EventHandler<ActionEvent> event = animation.getOnFinished();
		if(event != null)
		{
			animation.setOnFinished(e -> {event.handle(e);nextAnimation();});
		}
		else
		{
			animation.setOnFinished(e -> {nextAnimation();});
		}
		
		return animation;
	}

	
	private void nextAnimation()
	{
		if(!animations.isEmpty())
		{
			currentAnimation = animations.remove(0);
			currentAnimation.play();
		}
	}
}
