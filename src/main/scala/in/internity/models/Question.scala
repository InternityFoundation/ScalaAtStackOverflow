package in.internity.models

/**
  * @author Shivansh <shiv4nsh@gmail.com>
  * @since 6/1/18
  */
case class Questions(items: List[Question])

case class Owner(
                  reputation: Double,
                  user_id: Double,
                  user_type: String,
                  profile_image: String,
                  display_name: String,
                  link: String
                )

case class Question(
                     tags: List[String],
                     owner: Owner,
                     is_answered: Boolean,
                     view_count: Double,
                     answer_count: Double,
                     score: Double,
                     last_activity_date: Double,
                     creation_date: Double,
                     question_id: Double,
                     link: String,
                     title: String
                   )